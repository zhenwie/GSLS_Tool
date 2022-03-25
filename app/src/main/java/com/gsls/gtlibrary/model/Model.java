package com.gsls.gtlibrary.model;

import com.gsls.gt.GT;

/**
 * @Author PlayfulKing
 * @Date 2022/3/8 22:28
 * @CSDN https://blog.csdn.net/qq_39799899
 * @GitHub https://github.com/1079374315/GT
 * @Description:
 */
public class Model<T> extends GT.Frame.GT_Model<T>{

    public Model() {
    }

    public void show(){
        GT.logt("调用了 Model 里的show");
    }


}
