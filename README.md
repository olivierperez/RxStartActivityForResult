# RxStartActivyForResult

Use this service to convert a `startActivityForResult` into a ReactiveX Single.

## Example

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
