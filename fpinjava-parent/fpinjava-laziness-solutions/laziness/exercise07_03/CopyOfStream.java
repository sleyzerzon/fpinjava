package com.fpinjava.laziness.exercise07_03;


import com.fpinjava.common.Function;
import com.fpinjava.common.List;
import com.fpinjava.common.Option;
import com.fpinjava.common.Supplier;
import com.fpinjava.common.TailCall;
import com.fpinjava.common.Tuple;

import static com.fpinjava.common.TailCall.*;

public abstract class CopyOfStream<T> {

  @SuppressWarnings("rawtypes")
  private static CopyOfStream EMPTY = new Empty();

  public abstract T head();
  public abstract CopyOfStream<T> tail();
  public abstract boolean isEmpty();
  public abstract Option<T> headOption();
  protected abstract Head<T> headS();
  
  private CopyOfStream() {}
  
  public String toString() {
    return toList().toString();
  }
  
  public List<T> toList() {
    //return toListRecursive(this, List.list()).eval().reverse();
    return toListIterative();
  }
  
  @SuppressWarnings("unused")
  private TailCall<List<T>> toListRecursive(CopyOfStream<T> s, List<T> acc) {
    return s instanceof Empty
        ? ret(acc)
        : sus(() -> toListRecursive(s.tail(), List.cons(s.head(), acc)));
  }

  public List<T> toListIterative() {
    java.util.List<T> result = new java.util.ArrayList<>();
    CopyOfStream<T> ws = this;
    while (!ws.isEmpty()) {
      result.add(ws.head());
      ws = ws.tail();
    }
    return List.fromCollection(result);
  }
  
  public CopyOfStream<T> take(Integer n) {
    return n <= 0
        ? CopyOfStream.empty()
        : CopyOfStream.cons(headS(), tail().take(n - 1));
  }
  
  public CopyOfStream<T> drop(int n) {
    return n <= 0
        ? this
        : tail().drop(n - 1);
  }
  
  public CopyOfStream<T> takeWhile(Function<T, Boolean> p) {
    return isEmpty()
        ? this
        : p.apply(head()) 
            ? cons(headS(), tail().takeWhile(p))
            : empty();
  }

  public static class Empty<T> extends CopyOfStream<T> {

    private Empty() {
    }

    @Override
    public boolean isEmpty() {
      return true;
    }

    @Override
    public T head() {
      throw new IllegalStateException("head called on Empty stream");
    }

    @Override
    protected Head<T> headS() {
      throw new IllegalStateException("headS called on Empty stream");
    }

    @Override
    public CopyOfStream<T> tail() {
      throw new IllegalStateException("tail called on Empty stream");
    }

    @Override
    public Option<T> headOption() {
      return Option.none();
    }
  }

  public static class Cons<T> extends CopyOfStream<T> {

    protected Head<T> head;
    
    protected final CopyOfStream<T> tail;
    
    private Cons(Head<T> head, CopyOfStream<T> tail) {
      this.head = head;
      this.tail = tail;
    }

    @Override
    public boolean isEmpty() {
      return false;
    }

    @Override
    public T head() {
      return this.head.getEvaluated();
    }

    @Override
    public Head<T> headS() {
      return this.head;
    }

    @Override
    public CopyOfStream<T> tail() {
      return this.tail;
    }

    @Override
    public Option<T> headOption() {
      return Option.some(this.head());
    }
  }

  public static <T> CopyOfStream<T> cons(Supplier<T> hd, CopyOfStream<T> tl) {
    return new Cons<T>(new Head<T>(hd), tl);
  }

  public static <T> CopyOfStream<T> cons(Head<T> hd, CopyOfStream<T> tl) {
    return new Cons<T>(hd, tl);
  }

  @SuppressWarnings("unchecked")
  public static <T> CopyOfStream<T> empty() {
    return EMPTY;
  }

  public static <T> CopyOfStream<T> cons(List<T> list) {
    return list.isEmpty()
        ? empty()
        : new Cons<T>(new Head<T>(() -> list.head(), list.head()), cons(list.tail()));
  }

  @SafeVarargs
  public static <T> CopyOfStream<T> cons(T... t) {
    return cons(List.list(t));
  }
  
  public static class Head<T> {
    
    private Supplier<T> nonEvaluated;
    private T evaluated;
    
    public Head(Supplier<T> nonEvaluated) {
      super();
      this.nonEvaluated = nonEvaluated;
    }

    public Head(Supplier<T> nonEvaluated, T evaluated) {
      super();
      this.nonEvaluated = nonEvaluated;
      this.evaluated = evaluated;
    }

    public Supplier<T> getNonEvaluated() {
      return nonEvaluated;
    }

    public T getEvaluated() {
      if (evaluated == null) {
        evaluated = nonEvaluated.get();
      }
      return evaluated;
    }
  }
}
