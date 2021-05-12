package com.ayyaz.rxjavabasic

import android.util.Log
import com.ayyaz.rxjavabasic.model.User
import com.ayyaz.rxjavabasic.model.UserProfile
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit


val justList = mutableListOf<Int>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

val arrayList1 = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
val arrayList2 = arrayOf(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110)
val userList = mutableListOf<User>(
    User(1, "Ayyaz", 30),
    User(2, "Deepak", 25),
    User(3, "Deepak", 25),
    User(4, "Ruhil", 22),
    User(5, "Ruhil", 22),
    User(6, "Pratik", 27),
    User(7, "Ronil", 26)
)

fun justOperator() {

    val observable = Observable.just(justList)

    val observer = object : Observer<List<Int>> {
        override fun onSubscribe(d: Disposable?) {
            Log.d(MainActivity.TAG, "onSubscribe: ")
        }

        override fun onNext(t: List<Int>?) {
            Log.d(MainActivity.TAG, "onNext: $t")
        }

        override fun onError(e: Throwable?) {
            Log.e(MainActivity.TAG, "onError: ", e)
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG, "onComplete: ")
        }

    }

    observable.subscribe(observer)

}

fun arrayOperator() {
    val observable = Observable.fromArray(arrayList1, arrayList2)

    val observer = object : Observer<Array<Int>> {
        override fun onSubscribe(d: Disposable?) {
            Log.d(MainActivity.TAG, "onSubscribe: ")
        }

        override fun onNext(t: Array<Int>?) {
            Log.d(MainActivity.TAG, "onNext: $t")
        }

        override fun onError(e: Throwable?) {
            Log.e(MainActivity.TAG, "onError: ", e)
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG, "onComplete: ")
        }

    }

    observable.subscribe(observer)
}

fun iterableOperator() {
    val observable = Observable.fromIterable(justList)

    val observer = object : Observer<Int> {
        override fun onSubscribe(d: Disposable?) {
            Log.d(MainActivity.TAG, "onSubscribe: ")
        }

        override fun onNext(t: Int?) {
            Log.d(MainActivity.TAG, "onNext: $t")
        }

        override fun onError(e: Throwable?) {
            Log.e(MainActivity.TAG, "onError: ", e)
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG, "onComplete: ")
        }

    }

    observable.subscribe(observer)
}

fun rangeOperator() {
    Observable.range(1, 10).subscribe({
        Log.d(MainActivity.TAG, "onNext: $it")
    }, {
        Log.e(MainActivity.TAG, "onError: ", it)
    }, {
        Log.d(MainActivity.TAG, "onComplete: ")
    })
}

fun repeatOperator() {
    Observable.range(1, 10).repeat(2).subscribe({
        Log.d(MainActivity.TAG, "onNext: $it")
    }, {
        Log.e(MainActivity.TAG, "onError: ", it)
    }, {
        Log.d(MainActivity.TAG, "onComplete: ")
    })
}

fun intervalOperator() {
    Observable.interval(1, TimeUnit.SECONDS).takeWhile { v -> v <= 10 }.subscribe({
        Log.d(MainActivity.TAG, "onNext: $it")
    }, {
        Log.e(MainActivity.TAG, "onError: ", it)
    }, {
        Log.d(MainActivity.TAG, "onComplete: ")
    })
}

fun intervalRangeOperator() {
    Observable.intervalRange(100, 999999, 0, 1, TimeUnit.SECONDS).subscribe({
        Log.d(MainActivity.TAG, "onNext: $it")
    }, {
        Log.e(MainActivity.TAG, "onError: ", it)
    }, {
        Log.d(MainActivity.TAG, "onComplete: ")
    })
}

fun timeOperator() {
    Observable.timer(10, TimeUnit.SECONDS).subscribe({
        Log.d(MainActivity.TAG, "onNext: $it")
    }, {
        Log.e(MainActivity.TAG, "onError: ", it)
    }, {
        Log.d(MainActivity.TAG, "onComplete: ")
    })
}

fun createOperator() {
    Observable.create(ObservableOnSubscribe<Int> {
        try {
            for (i in justList) {
                it.onNext(i * 5)
            }

            it.onComplete()

        } catch (e: Exception) {
            it.onError(e)
        }
    }).subscribe({
        Log.d(MainActivity.TAG, "onNext: $it")
    }, {
        Log.e(MainActivity.TAG, "onError: ", it)
    }, {
        Log.d(MainActivity.TAG, "onComplete: ")
    })
}

fun filterOperator() {

    Observable.fromIterable(userList)
        .filter { user -> (user.age <= 25) }
        .subscribe({
            Log.d(MainActivity.TAG, "onNext: $it")
        }, {
            Log.e(MainActivity.TAG, "onError: ", it)
        }, {
            Log.d(MainActivity.TAG, "onComplete: ")
        })

}

fun lastOperator() {

    Observable.fromIterable(userList)
        .last(User(0, "demo", 0))
//        .lastElement()
//        .lastOrError()
        .subscribe({
            Log.d(MainActivity.TAG, "onNext: $it")
        }, {
            Log.e(MainActivity.TAG, "onError: ", it)
        })

}

fun distinctOperator() {

    Observable.fromIterable(userList)
        .distinct { user -> user.age }
//        .distinct()
        .subscribe({
            Log.d(MainActivity.TAG, "onNext: $it")
        }, {
            Log.e(MainActivity.TAG, "onError: ", it)
        }, {
            Log.d(MainActivity.TAG, "onComplete: ")
        })

}

fun skipOperator() {

    Observable.fromIterable(userList)
        .skip(2)
        .subscribe({
            Log.d(MainActivity.TAG, "onNext: $it")
        }, {
            Log.e(MainActivity.TAG, "onError: ", it)
        }, {
            Log.d(MainActivity.TAG, "onComplete: ")
        })

}

fun bufferOperator() {

    Observable.fromIterable(userList)
        .buffer(2)
        .subscribe({
            Log.d(MainActivity.TAG, "onNext: $it")
        }, {
            Log.e(MainActivity.TAG, "onError: ", it)
        }, {
            Log.d(MainActivity.TAG, "onComplete: ")
        })

}

fun mapOperator() {

    Observable.fromIterable(userList)
        .map { user ->
            UserProfile(user.id, user.name, user.age, "http://wwww.test.com/${user.id}.jpg")
        }
        .subscribe({
            Log.d(MainActivity.TAG, "onNext: $it")
        }, {
            Log.e(MainActivity.TAG, "onError: ", it)
        }, {
            Log.d(MainActivity.TAG, "onComplete: ")
        })

}