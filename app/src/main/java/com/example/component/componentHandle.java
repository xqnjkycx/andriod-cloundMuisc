/**
 * 我们将安卓上的任何区域都可看成是一块组件，我们将这些组件抽象为一个抽象类。
 * 因为绝大多数组件都具有 初始化、数据请求、数据整理、视图绑定等多个共同功能，我们将其都抽象为各种各样的抽象方法
 * 让子类去实现这些抽象方法
 * */

package com.example.component;

public abstract class componentHandle {
    //处理数据请求
    public abstract void dataRequest();
    //组件初始化
    public abstract void init();
}
