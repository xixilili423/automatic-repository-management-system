package com.service;

import com.vo.R;
import com.vo.param.*;

public interface RegionAndShelfService {
    R searchRegion(String id, String params);

    R searchShelf(String id, searchShelfparam params);

    R addShelf(String id, addShelfparam params);

    R allShelf(String id, String params);

    R deleteShelf(String id, deleteShelfparam params);
}
