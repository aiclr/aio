<div style="text-align: center;font-size: 40px;">循环依赖</div>

> 请规避循环依赖的代码

## 通过构造器注入构成的循环依赖

```java
/**
 * A depend B
 * B depend C
 * C depend A
 */
@Component
public class StudentA{
    private StudentB studentB;
    public void setStudentB(StudentB studentB){
        this.studentB=studentB;
    }
    public StudentA(){
        
    }
    @Autowired
    public StudentA(StudentB studentB){
        this.studentB=studentB;
    }
}

@Component
public class StudentB{
    private StudentC studentC;
    public void setStudentC(StudentC studentC){
        this.studentC=studentC;
    }
    public StudentB(){

    }
    @Autowired
    public StudentB(StudentC studentC){
        this.studentC=studentC;
    }
}

@Component
public class StudentC{
    private StudentA studentA;
    public void setStudentA(StudentA studentA){
        this.studentA=studentA;
    }
    public StudentC(){

    }
    @Autowired
    public StudentC(StudentA studentA){
        this.studentA=studentA;
    }
}
```

## @Lazy 懒加载解决循环依赖 

```java
@Component
public class StudentA{
    private StudentB studentB;
    public void setStudentB(StudentB studentB){
        this.studentB=studentB;
    }
    public StudentA(){

    }
    @Autowired
    public StudentA(@Lazy StudentB studentB){
        this.studentB=studentB;
    }

    public void print(){
        System.err.println("A");
    }
}
@Component
public class StudentB{
    private StudentC studentC;
    public void setStudentC(StudentC studentC){
        this.studentC=studentC;
    }
    public StudentB(){

    }
    @Autowired
    public StudentB(@Lazy StudentC studentC){
        this.studentC=studentC;
    }

    public void print(){
        System.err.println("B");
    }
}
@Component
public class StudentC{
    private StudentA studentA;
    public void setStudentA(StudentA studentA){
        this.studentA=studentA;
    }
    public StudentC(){

    }
    @Autowired
    public StudentC(@Lazy StudentA studentA){
        this.studentA=studentA;
    }

    public void print(){
        System.err.println("C");
    }
}
```