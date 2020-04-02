package GUI;

import java.util.List;

public class BarrierVar {
    private Integer first;
    private Integer second;
    private List<Integer> third;

    BarrierVar(Integer first, Integer second, List<Integer> third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public Integer getFirst() {
        return this.first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getSecond() {
        return this.second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public List<Integer> getThird() { return this.third; }

    public void setThird(List<Integer> third) { this.third = third; }

    public String toString() {
        return this.first + " " + this.second + " " + this.third;
    }
}

