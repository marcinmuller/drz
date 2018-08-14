package pl.mmuller.tree3;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
public class Node {
    @Id
    @GeneratedValue
    private long id;
    private double number;
    private double sum;
    private Node parent;
    private List<Node> children;

    public Node(){
        parent=null;
        children=new LinkedList<Node>();
    }

    public Node(Node node){
        this();
        this.number=node.getNumber();
        this.sum=node.getSum();
        this.parent=node.getParent();
        for(Node c: node.getChildren()){
            this.addChild(new Node(c));
        }
    }

    public Node(Node parent, double number){
        this();
        this.parent=parent;
        this.number=number;
        this.sum=getAncestorsSum(this);
    }

    public double getAncestorsSum(Node node){
        double sum=node.getNumber();
        while (!(node.isRoot())){
            node=node.getParent();
            sum+=node.getNumber();
        };
        return sum;
    }

    public boolean isRoot(){
        return (parent==null)?true:false;
    }

    public boolean isLeaf(){
        return children.isEmpty();
    }

    public void addChild(Node ...child) {
        for (Node c: child) {
            c.setParent(this);
            children.add(c);
        }
    }

    public void removeChild(Node node) {
        children.remove(node);
    }

    @Override
    public String toString(){
        return "[number="+number+" sum="+sum+"]";
    }

    public Node getLeftMostChild() {
        if (children.isEmpty()) return null;
        return children.get(0);
    }

    public Node getRightSibling() {
        if (parent != null) {
            List<Node> parentsChildren = parent.getChildren();
            int pos = parentsChildren.indexOf(this);
            if (pos < (parentsChildren.size()-1))
                return parentsChildren.get(pos+1);
        }
        return null;
    }
}
