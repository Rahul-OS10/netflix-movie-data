package com.netflix.netflixdataservice.serviceTest;

import com.netflix.netflixdataservice.Entity.NetflixData;
import com.netflix.netflixdataservice.Repository.NetflixRepository;
import com.netflix.netflixdataservice.Service.NetflixService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.netflix.netflixdataservice.Entity.NetflixData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ServiceTest {

    @Mock
    NetflixService service ;

    @Mock
    NetflixRepository repository;

    NetflixData newData = new NetflixData("100","18","above 18 years can watch", Collections.EMPTY_LIST,Collections.EMPTY_LIST,2018,140,"Fast and Furious","Thriller",8.5);

    NetflixData updateNetflixData ;



    @Test
    public void givenNewData_whenSave_thenSaveInDB()
    {
        service.saveNetflixData(newData);
        when(repository.save(newData)).thenReturn(newData);
        Assertions.assertTrue(!newData.getId().isEmpty());
    }

//    @Test
//    public void givenUpdatedData_whenUpdate_thenSaveUpdatedData()
//    {
//        service.updateNetflixData("Fast and Furious2" , updateNetflixData);
//        when(repository.updateByTitle("Fast and Furious2")).thenReturn(updateNetflixData);
//
//    }

    @Test
    public void givenTitle_whenFindByTitle_thenReturnNetflixDataObject()
    {
        String title = "Fast and Furious2";
        service.findByTitle(title);
        when(repository.findByTitle(title)).thenReturn(Optional.of(List.of(newData)));
        Assertions.assertNotNull(newData);
        Assertions.assertEquals(title,newData.getTitle());
    }
}