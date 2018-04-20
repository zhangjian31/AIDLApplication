// IMyAidl.aidl
package com.aidl.test;

// Declare any non-default types here with import statements
import com.aidl.test.bean.Person;
interface IMyAidl {
    /**
    *除了基本数据类型，其它类型的参数都要指明方向类型
    * in(输入) out(输出)  intou(输入输出)
    */
    void addPerson(in  Person person);
    List<Person> getPersonList();
}
