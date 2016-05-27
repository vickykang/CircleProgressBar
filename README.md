# CircleProgressBar
Circular progress bar

### Use
#### In XML
```
<com.vivam.circleprogressbar.widget.CircleProgressBar
        android:id="@+id/circle_pb"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:max="100"
        android:progress="20"
        android:secondaryProgress="25"
        android:thickness="5"
        app:progressBackground="#e0e0e0"
        app:progressColor="#004d40"
        app:secondaryProgressColor="#bdbdbd" />
```

#### In Java
```
CircleProgressBar circleProgressBar = (CircleProgressBar) findViewById(R.id.circle_pb);
circleProgressBar.setMax(100);
circleProgressBar.setProgress(20);
circleProgressBar.setSecondaryProgress(25);
circleProgressBar.setProgressBackground(0xffe0e0e0);
circleProgressBar.setProgressColor(0xff004d40);
circleProgressBar.setSecondaryProgressColor(0xffbdbdbd);
```
