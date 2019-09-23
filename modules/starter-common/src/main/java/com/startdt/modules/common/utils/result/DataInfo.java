package com.startdt.modules.common.utils.result;

import java.io.Serializable;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/23 下午3:28
 * @Modified By:
 */
public class DataInfo<T>  implements Serializable{

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> DataInfo<T> resultToData(T data){
        DataInfo<T> dataInfo = new DataInfo<>();

        dataInfo.setData(data);

        return dataInfo;
    }
}
