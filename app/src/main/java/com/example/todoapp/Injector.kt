package com.example.todoapp

import android.app.Activity
import android.support.v4.app.Fragment

val Activity.injector: TodoAppApplicationComponent
    get() {
        return (this.applicationContext as TodoAppApplication).injector
    }

val Fragment.injector: TodoAppApplicationComponent
    get() {
        return (this.context!!.applicationContext as TodoAppApplication).injector
    }

