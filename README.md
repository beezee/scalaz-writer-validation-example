###Writer[Validation[A, B]]

Simple example of using a Writer[Validation] to do both logging and
error handling.

```
sbt console
```

```scala
WriterExample.makeUser(17, "foo")
res0: main.WriterExample.LoggedValidation[main.WriterExample.User] = WriterT((List(Checking age, Checking email),Failure(Too youngNot a valid email)))

WriterExample.makeUser(27, "foo")
res1: main.WriterExample.LoggedValidation[main.WriterExample.User] = WriterT((List(Checking age, Checking email),Failure(Not a valid email)))

WriterExample.makeUser(27, "f@b")
res2: main.WriterExample.LoggedValidation[main.WriterExample.User] = WriterT((List(Checking age, Checking email),Failure(Too short)))

WriterExample.makeUser(27, "f@b.c")
res3: main.WriterExample.LoggedValidation[main.WriterExample.User] = WriterT((List(Checking age, Checking email),Success(User(27,f@b.c))))
```
