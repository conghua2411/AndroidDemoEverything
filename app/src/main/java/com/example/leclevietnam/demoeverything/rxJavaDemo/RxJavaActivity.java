package com.example.leclevietnam.demoeverything.rxJavaDemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.schedulers.SingleScheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.Single;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.leclevietnam.demoeverything.R;
import com.example.leclevietnam.demoeverything.databinding.ActivityRxJavaBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class RxJavaActivity extends AppCompatActivity {

    private static final String TAG = "RxJavaActivity";

    private ActivityRxJavaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java);

    }

    // simple
    private void simple() {
        Observable.just("1", "2", "3")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: " + d.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: ");
                        binding.setMagicText(binding.getMagicText() + "\n" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                        binding.setMagicText(binding.getMagicText() + "onComplete");
                    }
                });
    }

    // map
    private void map() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    emitter.onNext("wtf");
                    emitter.onNext("asd");
                    emitter.onComplete();
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + "umbala";
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: ");
                        binding.setMagicText(binding.getMagicText() + "\n" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                        binding.setMagicText(binding.getMagicText() + "\n" + "onComplete");
                    }
                });
    }

    // zip
    private void zip() {
        Observable<List<Integer>> observable1 = Observable.create(new ObservableOnSubscribe<List<Integer>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Integer>> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    emitter.onNext(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io());

        Observable<List<Integer>> observable2 = Observable.create(new ObservableOnSubscribe<List<Integer>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Integer>> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    emitter.onNext(Arrays.asList(2, 11, 12, 13, 14, 3, 6));
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2,
                new BiFunction<List<Integer>, List<Integer>, List<Integer>>() {
                    @Override
                    public List<Integer> apply(List<Integer> integers, List<Integer> integers2) throws Exception {
                        List<Integer> kq = new ArrayList<>();
                        for (int i : integers) {
                            for (int j : integers2) {
                                if (i == j) {
                                    kq.add(i);
                                }
                            }
                        }
                        return kq;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        binding.setMagicText("onSubscribe");
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        binding.setMagicText(binding.getMagicText() + "\n" + integers.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        binding.setMagicText(binding.getMagicText() + "\n" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        binding.setMagicText(binding.getMagicText() + "\nonComplete");
                    }
                });
    }

    // take
    private void take() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        binding.setMagicText("onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        binding.setMagicText(binding.getMagicText() + "\n" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        binding.setMagicText(binding.getMagicText() + "\n" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        binding.setMagicText(binding.getMagicText() + "\nonComplete");
                    }
                });
    }

    // timer
    private void timer() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        binding.setMagicText("onSubscribe");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        binding.setMagicText(binding.getMagicText() + "\n" + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        binding.setMagicText(binding.getMagicText() + "\n" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        binding.setMagicText(binding.getMagicText() + "\nonComplete");
                    }
                });
    }

    // interval
    private void interval() {
        Observable.interval(0, 2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        binding.setMagicText("onSubscribe");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        binding.setMagicText(binding.getMagicText() + "\n" + aLong);
                        if (aLong > 9) {
                            this.onComplete();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        binding.setMagicText(binding.getMagicText() + "\n" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        binding.setMagicText(binding.getMagicText() + "\nonComplete");
                    }
                });
    }

    // single
    private void single() {
//        Single.just("hello")
//                .subscribe(new SingleSubscriber<String>() {
//                    @Override
//                    public void onSuccess(String s) {
//                        binding.setMagicText("onSuccess" + "\n" + s);
//                    }
//
//                    @Override
//                    public void onError(Throwable error) {
//                        binding.setMagicText("onSuccess" + "\n" + error.getLocalizedMessage());
//                    }
//                });

        Single<String> single1 = Single.just("hello");
        Single<String> single2 = Single.just("world");

        Single.zip(single1, single2, new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) throws Exception {
                return s + s2;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        binding.setMagicText("onSubscribe");
                    }

                    @Override
                    public void onSuccess(String s) {
                        binding.setMagicText(binding.getMagicText() + "\nonSuccess\n" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        binding.setMagicText(binding.getMagicText() + "\n" + e.getLocalizedMessage());
                    }
                });
    }

    // completable
    private void completable() {
        Completable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getCompletableObserver());
    }

    private CompletableObserver getCompletableObserver() {
        return new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                binding.setMagicText(binding.getMagicText() + "\nonSubscribe");
            }

            @Override
            public void onComplete() {
                binding.setMagicText(binding.getMagicText() + "\nonComplete");
            }

            @Override
            public void onError(Throwable e) {
                binding.setMagicText(binding.getMagicText() + "\n" + e.getLocalizedMessage());
            }
        };
    }

    // flowable
    private void flowable() {

//        Flowable<Integer> observable = Flowable.just(1, 2, 3, 4);
//        observable.reduce(15, new BiFunction<Integer, Integer, Integer>() {
//            @Override
//            public Integer apply(Integer integer, Integer integer2) throws Exception {
////                Log.d(TAG, "apply: 1: " + integer + ", 2: " + integer2);
//                return integer + integer2;
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        binding.setMagicText("onSubscribe");
//                    }
//
//                    @Override
//                    public void onSuccess(Integer integer) {
//                        binding.setMagicText(binding.getMagicText() + "\nonSuccess\n" + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        binding.setMagicText(binding.getMagicText() + "\n" + e.getLocalizedMessage());
//                    }
//                });

        Observable.just(1, 2, 3, 4, 5, 6)
                .reduceWith(new Callable<List<Integer>>() {
                    @Override
                    public List<Integer> call() throws Exception {
                        return new ArrayList<>();
                    }
                }, new BiFunction<List<Integer>, Integer, List<Integer>>() {
                    @Override
                    public List<Integer> apply(List<Integer> integers, Integer integer) throws Exception {
                        integers.add(integer);
                        return integers;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        binding.setMagicText("onSubscribe");
                    }

                    @Override
                    public void onSuccess(List<Integer> list) {
                        binding.setMagicText(binding.getMagicText() + "\nonSuccess\n" + list.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        binding.setMagicText(binding.getMagicText() + "\nonError\n" + e.getLocalizedMessage());
                    }
                });
    }

    // maybe
    private void maybeReduce() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        binding.setMagicText("onSubscribe");
                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        binding.setMagicText(binding.getMagicText() + "\nonSuccess\n" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        binding.setMagicText(binding.getMagicText() + "\nonError\n " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        binding.setMagicText(binding.getMagicText() + "\nonComplete");
                    }
                });
    }

    // buffer
    private void buffer() {
//        Observable.just(1,2,3,4,5)
//                .buffer(2)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<List<Integer>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        binding.setMagicText("onSubscribe");
//                    }
//
//                    @Override
//                    public void onNext(List<Integer> integers) {
//                        binding.setMagicText(binding.getMagicText() + "\nonNext\n" + integers.toString());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        binding.setMagicText(binding.getMagicText() + "\nonError");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        binding.setMagicText(binding.getMagicText() + "\nonComplete");
//                    }
//                });

        Observable.just(1,2,3,4,5,6,7)
                .buffer(3, new Callable<List<Integer>>() {
                    @Override
                    public List<Integer> call() {
                        List<Integer> list = new ArrayList<>();

                        list.add(-1);
                        list.add(0);
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        binding.setMagicText("onSubscribe");
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        binding.setMagicText(binding.getMagicText() + "\nonNext\n" + integers.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        binding.setMagicText(binding.getMagicText() + "\nonError\n" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        binding.setMagicText(binding.getMagicText() + "\nonComplete");
                    }
                });
    }

    // filter
    private void filter() {
//        Observable.just(1,2,3,4,5,6)
//                .filter(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) {
//                        return integer%2 == 0;
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        binding.setMagicText("onSubscribe");
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        binding.setMagicText(binding.getMagicText() + "\nonNext\n" + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        binding.setMagicText(binding.getMagicText() + "\nonError\n" + e.getLocalizedMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        binding.setMagicText(binding.getMagicText() + "\nonComplete");
//                    }
//                });

        Observable.just(1,2,3,4,5,6,7,8,9)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        return integer%2 == 0;
                    }
                })
                .buffer(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        binding.setMagicText("onSubscribe");
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        binding.setMagicText(binding.getMagicText() + "\nonNext\n" + integers.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        binding.setMagicText(binding.getMagicText() + "\nonError\n" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        binding.setMagicText(binding.getMagicText() + "\nonComplete");
                    }
                });
    }

    public void showMagic(View v) {
        filter();
    }
}
