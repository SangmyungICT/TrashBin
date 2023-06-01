package com.trashbin;

import com.trashbin.domain.TrashBinEntity;
import com.trashbin.dto.ClientDto;
import com.trashbin.repository.TrashBinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetNearPin {

    private static final int EARTH_RADIUS = 6371;

    private final TrashBinRepository trashBinRepository;


    public List<TrashBinEntity> nearTrashBinList(ClientDto.ClientGPSDistanceDto clientGpsDistanceDto) {
        //현재 위도 좌표 (y 좌표)
        double nowLatitude = clientGpsDistanceDto.getLatitude();
        //현재 경도 좌표 (x 좌표)
        double nowLongitude = clientGpsDistanceDto.getLongitude();

        //m당 y 좌표 이동 값
        double mForLatitude =(1 /(EARTH_RADIUS* 1 *(Math.PI/ 180)))/ 1000;
        //m당 x 좌표 이동 값
        double mForLongitude =(1 /(EARTH_RADIUS* 1 *(Math.PI/ 180)* Math.cos(Math.toRadians(nowLatitude))))/ 1000;

        //현재 위치 기준 검색 거리 좌표
        double maxLatitude = nowLatitude +(clientGpsDistanceDto.getDistance()* mForLatitude);
        double minLatitude = nowLatitude -(clientGpsDistanceDto.getDistance()* mForLatitude);
        double maxLongitude = nowLongitude +(clientGpsDistanceDto.getDistance()* mForLongitude);
        double minLongitude = nowLongitude -(clientGpsDistanceDto.getDistance()* mForLongitude);

        //해당되는 좌표의 범위 안에 있는 가맹점
        return trashBinRepository.getNearTrashBinEntities(minLatitude, maxLatitude, minLongitude, maxLongitude);
    }

}
