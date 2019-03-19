package pl.tscript3r.notify.monitor.api.v1.mapper;

import com.google.common.collect.Sets;
import org.junit.Test;
import pl.tscript3r.notify.monitor.api.v1.model.AdDTO;
import pl.tscript3r.notify.monitor.domain.Ad;
import pl.tscript3r.notify.monitor.domain.Task;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AdMapperTest {

    public static final long ID1 = 1L;
    public static final long ID2 = 2L;
    public static final long ID3 = 3L;
    public static final String URL = "https://www.olx.pl/test/";
    public static final String KEY = "test";
    public static final String VALUE = "testable";
    private AdMapper adMapper = AdMapper.INSTANCE;

    @Test
    public void adToAdDTO() {
        Ad ad = new Ad(Task.builder()
                .usersId(Sets.newHashSet(ID1, ID2, ID3)).build(), URL);
        ad.addProperty(KEY, VALUE);
        AdDTO adDTO = adMapper.adToAdDTO(ad);
        assertEquals(ad.getTask().getUsersId(), adDTO.getUsersId());
        assertEquals(ad.getUrl(), adDTO.getUrl());
        assertEquals(ad.getAdditionalProperties(), adDTO.getAdditionalProperties());
        assertEquals(ad.getTimestamp(), adDTO.getTimestamp());
        assertEquals(ad.getId(), adDTO.getId());
    }

}