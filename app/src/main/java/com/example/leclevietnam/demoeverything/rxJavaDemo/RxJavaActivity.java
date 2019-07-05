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
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.Single;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.leclevietnam.demoeverything.R;
import com.example.leclevietnam.demoeverything.databinding.ActivityRxJavaBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class RxJavaActivity extends AppCompatActivity {

    private static final String TAG = "RxJavaActivity";

    private ActivityRxJavaBinding binding;

//    private Observable<String> btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java);

        binding.setMagicText("");

        // btn click more than 3 time
//        btnClick = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
//                binding.btnClick.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        emitter.onNext(String.valueOf(System.currentTimeMillis()));
//                    }
//                });
//            }
//        });
//
//        btnClick
//                .buffer(1000, TimeUnit.MILLISECONDS)
//                .filter(new Predicate<List<String>>() {
//                    @Override
//                    public boolean test(List<String> strings) throws Exception {
//                        return strings.size() >= 3;
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this.<List<String>>getObserver());

        binding.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMagic();
            }
        });
    }

    // get observer
    private <T> Observer<T> getObserver() {
        return new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("rxJavaTime", "onSubscribe time : " + new Date(System.currentTimeMillis()));
                binding.setMagicText(binding.getMagicText() + "\nonSubscribe");
            }

            @Override
            public void onNext(T t) {
                Log.d("rxJavaTime", "onNext time : " + new Date(System.currentTimeMillis()));
                binding.setMagicText(binding.getMagicText() + "\nonNext : " + t.toString());
            }

            @Override
            public void onError(Throwable e) {
                binding.setMagicText(binding.getMagicText() + "\nonError : " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                Log.d("rxJavaTime", "onComplete time : " + new Date(System.currentTimeMillis()));
                binding.setMagicText(binding.getMagicText() + "\nonComplete");
            }
        };
    }

    // simple
    private void simple() {
        Observable.just("1", "2", "3")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.<String>getObserver());
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
                .subscribe(this.<String>getObserver());
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
                .subscribe(this.<List<Integer>>getObserver());
    }

    // take
    private void take() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(3)
                .subscribe(this.<Integer>getObserver());
    }

    // timer
    private void timer() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.<Long>getObserver());
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

        Flowable.just(1, 2, 3, 4, 5, 6)
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

        Observable.just(1, 2, 3, 4, 5, 6, 7)
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
                .subscribe(this.<List<Integer>>getObserver());
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

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        return integer % 2 == 0;
                    }
                })
                .buffer(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.<List<Integer>>getObserver());
    }

    // skip
    private void skip() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .skip(2)
                .subscribe(this.<Integer>getObserver());
    }

    // scan
    private void scan() {
        // 1 - 3 - 6 - 10 -...
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                })
                .subscribe(this.<Integer>getObserver());
    }

    // replay
    private void replay() {
        PublishSubject<Integer> source = PublishSubject.create();
        ConnectableObservable<Integer> connectableObservable = source.replay(3);
        connectableObservable.connect();

        Observer<Integer> observer1 = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                binding.setMagicText(binding.getMagicText() + "\nobserver1 - onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                binding.setMagicText(binding.getMagicText() + "\nobserver1 - onNext : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                binding.setMagicText(binding.getMagicText() + "\nobserver1 - onError : " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                binding.setMagicText(binding.getMagicText() + "\nobserver1 - onComplete");
            }
        };

        Observer<Integer> observer2 = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                binding.setMagicText(binding.getMagicText() + "\nobserver2 - onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                binding.setMagicText(binding.getMagicText() + "\nobserver2 - onNext : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                binding.setMagicText(binding.getMagicText() + "\nobserver2 - onError : " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                binding.setMagicText(binding.getMagicText() + "\nobserver2 - onComplete");
            }
        };

        connectableObservable.subscribe(observer1);

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        source.onNext(4);
        source.onNext(5);
        source.onNext(6);
        source.onNext(7);

        connectableObservable.subscribe(observer2);

        source.onNext(8);
        source.onComplete();

    }

    // concat
    private void concat() {
        Observable<String> observable1 = Observable.just("a1", "a2", "a3", "a4", "a5", "a6");
        Observable<String> observable2 = Observable.just("b1", "b2", "b3", "b4");

        Observable.concat(observable1, observable2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.<String>getObserver());
    }

    // merge
    private void merge() {
        Observable<String> observable1 = Observable.just("a1", "a2", "a3", "a4", "a5", "a6");
        Observable<String> observable2 = Observable.just("b1", "b2", "b3", "b4");

        Observable.merge(observable2, observable1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.<String>getObserver());
    }

    // defer
    private void defer() {
//        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
//
//        Observable<List<Integer>> observable = Observable.fromArray(list)
//                .flatMap(new Function<List<Integer>, ObservableSource<List<Integer>>>() {
//                    @Override
//                    public ObservableSource<List<Integer>> apply(final List<Integer> integers) throws Exception {
//                        return Observable.defer(new Callable<ObservableSource<List<Integer>>>() {
//                            @Override
//                            public ObservableSource<List<Integer>> call() throws Exception {
//                                return Observable.fromArray(integers);
//                            }
//                        });
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//
////        list.addAll(Arrays.asList(10,11,12));
//
//        list.add(100);
//
//        observable.subscribe(this.<List<Integer>>getObserver());

        Car car = new Car();

        Observable<String> observable = car.getDeferObservable();

        car.setName("hello");

        observable.subscribe(this.<String>getObserver());
    }

    //distinct
    private void distinct() {
        Observable.fromArray(1, 12, 1, 3, 12, 3, 3, 4, 1, 23, 3, 1, 4, 23, 1, 3)
                .distinct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.<Integer>getObserver());
    }

    // last
    private void last() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .last(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        binding.setMagicText("onSubscribe");
                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        binding.setMagicText(binding.getMagicText() + "\nonSuccess : " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        binding.setMagicText(binding.getMagicText() + "\nonError : " + e.getLocalizedMessage());
                    }
                });
    }

    // replay subject
    private void replaySubject() {
        ReplaySubject<Integer> source = ReplaySubject.create();

        Observer<Integer> observer1 = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                binding.setMagicText("observer1 - onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                binding.setMagicText(binding.getMagicText() + "\nobserver1 - onNext : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                binding.setMagicText(binding.getMagicText() + "\nobserver1 - onError : " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                binding.setMagicText(binding.getMagicText() + "\nobserver1 - onComplete");
            }
        };

        Observer<Integer> observer2 = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                binding.setMagicText(binding.getMagicText() + "\nobserver2 - onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                binding.setMagicText(binding.getMagicText() + "\nobserver2 - onNext : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                binding.setMagicText(binding.getMagicText() + "\nobserver2 - onError : " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                binding.setMagicText(binding.getMagicText() + "\nobserver2 - onComplete");
            }
        };

        source.subscribe(observer1);

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        source.onNext(4);

        source.onComplete();
        source.subscribe(observer2);
    }

    // publish subject
    private void publishSubject() {
        PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject.subscribe(this.<String>getObserver());

        publishSubject.onNext("1");
        publishSubject.onNext("2");
        publishSubject.onNext("3");

        publishSubject.subscribe(this.<String>getObserver());

        publishSubject.onNext("4");
        publishSubject.onNext("5");
        publishSubject.onNext("6");
//        publishSubject.onError(new Throwable("wwwww"));
        publishSubject.onComplete();

    }

    // behavior subject
    private void behaviorSubject() {
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
        behaviorSubject.subscribe(this.<String>getObserver());

        behaviorSubject.onNext("1");
        behaviorSubject.onNext("2");
        behaviorSubject.onNext("3");

        behaviorSubject.subscribe(this.<String>getObserver());
        behaviorSubject.onNext("4");
        behaviorSubject.onComplete();

    }

    // async subject
    private void asyncSubject() {
        AsyncSubject<String> asyncSubject = AsyncSubject.create();

        asyncSubject.subscribe(this.<String>getObserver());

        asyncSubject.onNext("1");
        asyncSubject.onNext("2");
        asyncSubject.onNext("3");

        asyncSubject.subscribe(this.<String>getObserver());
//        asyncSubject.onNext("4");
//        asyncSubject.onNext("5");
        asyncSubject.onComplete();
    }

    // throttleFirst
    private void throttleFirst() {

        // the first one alway send
        // sleep more than t time from the last send -> send

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Thread.sleep(100);
                emitter.onNext("1");
                Thread.sleep(1000);
                emitter.onNext("2");
                Thread.sleep(499);
                emitter.onNext("3");
                Thread.sleep(700);
                emitter.onNext("4");
                emitter.onNext("5");
                Thread.sleep(1000);
                emitter.onNext("6");
                emitter.onComplete();
            }
        })
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.<String>getObserver());
    }

    // throttleLast
    private void throttleLast() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("1");
                Thread.sleep(1000);
                emitter.onNext("2");
                Thread.sleep(50);
                emitter.onNext("3");
                Thread.sleep(50);
                emitter.onNext("4");
                Thread.sleep(50);
                emitter.onNext("5");
                Thread.sleep(50);
                emitter.onNext("6");
                Thread.sleep(50);
                emitter.onNext("7");
                Thread.sleep(50);
                emitter.onNext("8");
                Thread.sleep(50);
                emitter.onNext("9");
                Thread.sleep(50);
                emitter.onNext("10");
                Thread.sleep(50);
                emitter.onNext("11");
                Thread.sleep(50);
                emitter.onNext("12");
                Thread.sleep(50);
                emitter.onNext("13");
                Thread.sleep(700);
                emitter.onNext("14");
                Thread.sleep(251);
                emitter.onComplete();
            }
        })
                .throttleLast(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.<String>getObserver());
    }

    // debounce
    private void debounce() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Thread.sleep(100);
                emitter.onNext(2);
                Thread.sleep(100);
                emitter.onNext(3);
                Thread.sleep(100);
                emitter.onNext(4);
                Thread.sleep(300);
                emitter.onNext(5);
                Thread.sleep(501);
                emitter.onNext(6);
                Thread.sleep(200);
                emitter.onNext(7);
                Thread.sleep(200);
                emitter.onComplete();
            }
        })
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.<Integer>getObserver());
    }

    // window
    private void window() {
        Observable.interval(1, TimeUnit.SECONDS).take(20)
                .window(4, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Observable<Long>>() {
                    @Override
                    public void accept(Observable<Long> longObservable) throws Exception {
                        binding.setMagicText(binding.getMagicText() + "\nNew window : ");
                        longObservable
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(Long aLong) throws Exception {
                                        binding.setMagicText(binding.getMagicText() + "\nNext: " + aLong);
                                    }
                                });
                    }
                });
    }

    // delay
    private void delay() {
        Observable.just(1, 2, 3, 4)
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.<Integer>getObserver());
    }

    // switchMap
    private void switchMap() {
        Observable.just(1, 2, 3, 4, 5)
                .switchMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return Observable.just(integer.toString() + "x");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.<String>getObserver());
    }

    // test 2 observable waiting
    private void testObser() {
        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Thread.sleep(3000);
                emitter.onNext("hello");
            }
        });

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Thread.sleep(2000);
                emitter.onNext("world");
            }
        });

        Observable.zip(observable1, observable2, new BiFunction<String, String, String>() {
            @Override
            public String apply(String o, String o2) throws Exception {
                return o + o2;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.<String>getObserver());
    }

    public void showMagic() {
        testObser();
    }

    class Car {
        String name;

        Car() {
            name = "1";
        }

        void setName(String name) {
            this.name = name;
        }

        Observable<String> getDeferObservable() {
            return Observable.defer(new Callable<ObservableSource<? extends String>>() {
                @Override
                public ObservableSource<? extends String> call() throws Exception {
                    return Observable.just(name);
                }
            });
        }
    }
}
