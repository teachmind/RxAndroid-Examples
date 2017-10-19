package me.shuza.rxsearchdemo;

import android.widget.SearchView;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 *
 * :=  created by:  Shuza
 * :=  create date:  10/18/2017
 * :=  (C) CopyRight Shuza
 * :=  www.shuza.me
 * :=  shuza.sa@gmail.com
 * :=  Fun  :  Coffee  :  Code
 *
 **/

public class RxSearchObservable {

    public static Observable<String> fromSearchView(SearchView searchView) {
        final PublishSubject<String> publisher = PublishSubject.create();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                publisher.onComplete();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                publisher.onNext(s);
                return true;
            }
        });
        return publisher;
    }
}
