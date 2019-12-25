package multiplethread;

import java.util.LinkedList;
import java.util.Stack;

//////栈
public class MyStack extends Stack {

	LinkedList<Object> list = new LinkedList<Object>();
	
	// 把数据推入到最后位置
	@Override
	public synchronized Object push(Object o) {
	  while (list.size() >= 10) {
	      try {
	          this.wait();
	      } catch (InterruptedException e) {
	          e.printStackTrace();
	      }
	  }
	  
		System.out.println("当前栈内数据: " + list);
	  list.addLast(o);
	  System.out.println("入栈: " + o);
	  this.notify();
	  return list;
	}
	
	// 把最后一个数据取出来
	@Override
	public synchronized Object pop() {
	  while (list.isEmpty()) {
	      try {
	          this.wait();
	      } catch (InterruptedException e) {
	          e.printStackTrace();
	      }
	  }
	  System.out.println("当前栈内数据: " + list);
	  Object c = list.removeLast();
	  System.out.println("出栈: " + c);
	  this.notify();
	  return c;
	}
	
	// 查看最后一个数据
	@Override
	public Object peek() {
	  return list.getLast();
	}
}