package com.trashbin.config;

import com.trashbin.domain.TrashBinEntity;
import com.trashbin.dto.ClientDto;
import com.trashbin.repository.TrashBinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetNearPin {

    private static final int EARTH_RADIUS = 6371;

    private final TrashBinRepository trashBinRepository;

    public List<TrashBinEntity> nearTrashBinList(ClientDto.GPSDistanceDto gpsDistanceDto) {
        //현재 위도 좌표 (y 좌표)
        double nowLatitude = gpsDistanceDto.getLatitude();
        //현재 경도 좌표 (x 좌표)
        double nowLongitude = gpsDistanceDto.getLongitude();

        //m당 y 좌표 이동 값
        double mForLatitude =(1 /(EARTH_RADIUS* 1 *(Math.PI/ 180)))/ 1000;
        //m당 x 좌표 이동 값
        double mForLongitude =(1 /(EARTH_RADIUS* 1 *(Math.PI/ 180)* Math.cos(Math.toRadians(nowLatitude))))/ 1000;

        //현재 위치 기준 검색 거리 좌표
        double maxLatitude = nowLatitude +(gpsDistanceDto.getDistance()* mForLatitude);
        double minLatitude = nowLatitude -(gpsDistanceDto.getDistance()* mForLatitude);
        double maxLongitude = nowLongitude +(gpsDistanceDto.getDistance()* mForLongitude);
        double minLongitude = nowLongitude -(gpsDistanceDto.getDistance()* mForLongitude);

        //해당되는 좌표의 범위 안에 있는 가맹점
        return trashBinRepository.getNearTrashBinEntities(minLatitude, maxLatitude, minLongitude, maxLongitude);
    }

}
