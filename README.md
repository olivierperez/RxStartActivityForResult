# RxStartActivityForResult

Android service to convert a `.startActivityForResult(intent, requestCode)` into a ReactiveX Single.

## Example

```kotlin
class MainActivity : AppCompatActivity() {

    private val startActivityService = StartActivityService(this)

}
```

```kotlin
startActivityService
    .startActivityForResult(
        intent = Intent(this, SecondActivity::class.java),
        requestCode = 1234
    )
    .observeOn(AndroidSchedulers.mainThread())
    .subscribeBy(
        onSuccess = { result: Result ->
            val time = result.data?.getLongExtra("TIME", -1) ?: -2
            val status = result.resultCode == Activity.RESULT_OK
            val requestCode = result.requestCode
            val message = "[$requestCode][$status] Time is: $time"

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    )
```

## Install

[![](https://jitpack.io/v/olivierperez/RxStartActivityForResult.svg)](https://jitpack.io/#olivierperez/RxStartActivityForResult)

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.olivierperez:RxStartActivityForResult:VERSION'
}
```

## Requirements

- AndroidX
