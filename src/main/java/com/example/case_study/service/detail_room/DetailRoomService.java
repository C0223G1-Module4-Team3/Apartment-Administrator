package com.example.case_study.service.detail_room;

import com.example.case_study.model.DetailRoom;
import com.example.case_study.repository.IDetailRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
//@Converter(autoApply = true)
public class DetailRoomService implements IDetailRoomService{
    @Autowired
    private IDetailRoomRepository detailRoomRepository;
    @Override
    public Page<DetailRoom> display(Pageable pageable) {
        return detailRoomRepository.findAll(pageable);
    }

    @Override
    public void save(DetailRoom detailRoom) {
        detailRoomRepository.saveRoomDetail(detailRoom.getRoom().getId(), detailRoom.getFacility().getId(), detailRoom.getAmount());
    }

    @Override
    public void edit(DetailRoom detailRoom) {
        List<DetailRoom> list = detailRoomRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (detailRoom.getId().equals(list.get(i).getId())){
                detailRoomRepository.save(detailRoom);
                break;
            }
        }
    }

    @Override
    public Page<DetailRoom> findByRoomId(Integer id,Pageable pageable) {
        return detailRoomRepository.findByRoomId(id,pageable);
    }

    @Override
    public void editAmount(DetailRoom detailRoom) {
        for (DetailRoom d:detailRoomRepository.findAll()) {
            if (detailRoom.getFacility().getId().equals(d.getFacility().getId())){
                d.setAmount(detailRoom.getAmount()+d.getAmount());
                detailRoomRepository.save(d);
            }
        }
    }

    @Override
    public boolean checkFacilityId(DetailRoom detailRoom) {
        for (DetailRoom d:detailRoomRepository.findAll()) {
            if (detailRoom.getFacility().getId().equals(d.getFacility().getId()) && detailRoom.getRoom().getId().equals(d.getRoom().getId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer getAllFacilityInRoom(Integer id) {
        Integer totalAmount = 0;
        for (DetailRoom d:detailRoomRepository.findAll()) {
            if (d.getRoom().getId().equals(id)){
                totalAmount += d.getAmount();
            }
        }
        return totalAmount;
    }

    @Override
    public DetailRoom findById(Integer id) {
        return detailRoomRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        for (DetailRoom d:detailRoomRepository.findAll()) {
            if (d.getId().equals(id)){
                detailRoomRepository.delete(d);
                break;
            }
        }
    }
}
