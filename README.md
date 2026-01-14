The application allows you to view a list of public repositories of the selected user. Repository data is retrieved using the GitHub API. If don't success get data from network, then data get from database. If data from database by user not have, then on screen show error message. If there is no data in the database for the user, an error message is displayed on the screen.

Application writed at Kotlin, with usage next libraries: Retrofit, Dagger2, Jetpack Compose, Room. I used Clean Architecture as Application Architecture and architecture pattern MVVM.

Install application can with apk file, located in folder setup.
