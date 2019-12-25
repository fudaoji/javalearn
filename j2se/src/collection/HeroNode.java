package collection;

import java.util.ArrayList;
import java.util.List;

import charactor.Hero;

public class HeroNode {

	// 左子节点
    public HeroNode leftNode;
    // 右子节点
    public HeroNode rightNode;
  
    // 值
    public Hero value;
  
    // 插入 数据
    public void add(Hero v) {
        // 如果当前节点没有值，就把数据放在当前节点上
        if (null == value)
            value = v;
  
        // 如果当前节点有值，就进行判断，新增的值与当前值的大小关系
        else {
            // 新增的值，比当前值小或者相同 
            if ((v.hp - value.hp) >= 0) {
                if (null == leftNode)
                    leftNode = new HeroNode();
                leftNode.add(v);
            } else {// 新增的值，比当前值大
                if (null == rightNode)
                    rightNode = new HeroNode();
                rightNode.add(v);
            }
        }
    }
    
 // 中序遍历所有的节点
    public List<Hero> values() {
        List<Hero> values = new ArrayList<>();
  
        // 左节点的遍历结果
        if (null != leftNode)
            values.addAll(leftNode.values());
  
        // 当前节点
        values.add(value);
  
        // 右节点的遍历结果
        if (null != rightNode)
            values.addAll(rightNode.values());
        return values;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hero randoms[] = new Hero[] { 
				new Hero(67), new Hero(7), new Hero(30), new Hero(73), new Hero(10), 
				new Hero(0), new Hero(78), new Hero(81), new Hero(10), new Hero(74) 
		};
		  
        HeroNode roots = new HeroNode();
        for (Hero h : randoms) {
            roots.add(h);
        }
        
        List<Hero> heros = roots.values();
        for (Hero h : heros) {
        	System.out.println(h.hp);
        }
        //System.out.println();
	}

}
