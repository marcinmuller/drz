package pl.mmuller.tree3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tree3ApplicationTests {

    @Test
    public void contextLoads() {
        Node n1 = new Node(null,5);
        Node n2 = new Node(n1, 6);
        Node n3 = new Node(n1,7);
        Node n4 = new Node(n1,10);
        Node n5 = new Node(n2,11);
        Node n6 = new Node(n2,20);
        Node n7 = new Node(n3, 5);

        n1.addChild(n2,n3,n4);
        n2.addChild(n5,n6);
        n3.addChild(n7);

        Tree drz = new Tree(n1);

//        drz.moveBranch(n4,n5);

//        Node n8 = new Node(n3, 100);
//        n3.addChild(n8);

        drz.copyBranch(n2,n4);

        //        drz.removeNode(n2);
//        drz.moveBranch(n2,n4);
//        drz.setDescendantsSum(n2);

        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println(drz);
        System.out.println();
        System.out.println();
    }

}
