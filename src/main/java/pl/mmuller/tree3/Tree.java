package pl.mmuller.tree3;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@Component
public class Tree {
    @Id
    @GeneratedValue
    private long id;
    private Node root;

    public Tree(Node root){
        this.root=root;
    }

    public Node setDescendantsSum (Node node) {
        node.setSum(node.getAncestorsSum(node));

        Node temp = node.getLeftMostChild();
        while (temp != null) {
            setDescendantsSum(temp);
            temp = temp.getRightSibling();
        }
        return node;
    }

    public void moveBranch(Node moved, Node node){
        moved.getParent().removeChild(moved);
        moved.setParent(node);
        node.addChild(moved);
        this.setDescendantsSum(moved);
    }

    public void copyBranch(Node copied, Node node){
        Node copyOfCopied=new Node(copied);
        copyOfCopied.setParent(node);
        node.addChild(copyOfCopied);
        this.setDescendantsSum(copyOfCopied);
    }

    public void removeNode(Node node){
        node.getParent().getChildren().remove(node);
    }

    private StringBuilder output;
    private void makeTreeStringBrackets(Node n) {
        output.append(n);
        Node temp = n.getLeftMostChild();
        if( temp != null) output.append("(");
        while (temp != null) {
            makeTreeStringBrackets(temp);
            temp = temp.getRightSibling();
            if (temp != null) output.append(", ");
            else output.append(")");
        }
    }
    @Override
    public String toString() {
        output = new StringBuilder();
        makeTreeStringBrackets(root);
        return output.toString();
    }
}
