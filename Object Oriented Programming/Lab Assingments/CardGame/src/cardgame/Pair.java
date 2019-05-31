package cardgame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jacobelliott
 */
public class Pair<L,R> {
    L l;
    R r;
    public Pair(L l, R r)
    {
        this.l = l;
        this.r = r;
    }
    public L getLeft()
    {
        return l;
    }
    public R getRight()
    {
        return r;
    }
}
