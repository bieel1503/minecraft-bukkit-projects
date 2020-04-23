package br.bieel.storage;

import br.bieel.storage.utils.Callable;

public abstract class StorageCallback<E> extends Callable {
    protected E result;

    public E getResult(){
        return this.result;
    }
    public void setResult(E e){
        this.result = e;
    }
}