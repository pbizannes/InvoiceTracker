# InvoiceTracker

## Specification / Requirements

This app shows a list of invoices and totals for a set of invoices
parsed from the provided JSON endpoint, which is configurable.

There is a different screen for loading, when finished loading with
valid results, when the list is empty, and when there is an error.

There are unit tests to protect against regressions

## Setup

To run this project, you can:
1. Run this in Android Studio.
2. Install either debug or release APKs bundled with this code

If you are running this in Android Studio, you can also configure the endpoints
by modifying app/build.gradle.kts, and changing either of both of the
BuildConfigField properties for the debug and/or release build. 

## Explanation of code

I initially did "Create new project" in Android Studio and chose a blank activity
for this project. I then looked again at the test instructions to see the structure
of the data, and confirmed the existing JSON matched this, which it did except for
the priceInCents field. I dealt with this difference in the serialisation.

## Architecture decisions

I created a feature directory called "invoiceviewer" as well, even though there is only one feature.
In a bigger project, there would be multiple features. In really big features,
these features could be separated into separate modules.

I used a NavigableListDetailPaneScaffold so that I automatically get
the List Detail pattern which changes with rotation.  I also
automatically get state saved in-between rotations using
rememberListDetailPaneScaffoldNavigator, which is an extension of
rememberSaveable which I would have probably used to save the state
between rotations. I know that some of this implementation is experimental,
but I am pretty certain this is how things are going in Android. The rotation
should work quite well.

## UI decisions

MVVM of course! Although, this is a bit like MVI without the events being
sent from the UI. Either way, there is reactive observation of the view
model state.

I tested DarkMode and LightMode on the emulator using these commands:

* adb -s emulator-5554 shell "cmd uimode night yes"
* adb -s emulator-5554 shell "cmd uimode night no"

I also ran this on a Pixel 6 device. It looks much better on the
device! However, I did not focus too much on making this look good.

## Third Party code

Third party code I used in the app:
- retrofit
  - to speed up development time.
- OkHttp
  - although so many people use it these days
  - even HttpURLConnection uses an older version of OkHttp
  - I could have used the new HttpClient classes, but I opted for retrofit which includes this


## Tests

I added tests of the Repository and the ViewModel. I considered using
Mockk for this, since I have used it before quite a bit, but it in
the end I opted for the regular JUnit and Mockito classes.

## Trade-offs

I would have liked to save the items in a Room database. I was
thinking of this when I created the directory source/local, but
I did not have time to implement it. While the state is saved
in between rotations, the information is lost when the app is
closed.

I decided to use the third-party library retrofit (and OkHttp) to
save time. Maybe something else is better.

I decided to use an experimental Material 3 API with the
ListDetailPaneScaffold, also to save time. You still need to
remember state, but it saves a bit of boilerplate code.

I opted not to use XML layouts, but I see that as a plus.

I considered making different flavours of the debug build
with the different URLs, but I chose not to do this since that is
not really how URLs should be configured.

Another addition I wanted to do was to make the URL configurable
via, not just the build.gradle.kts, but via an Environment variable.
This would then make it easy for a CI/CD pipeline to configure with
different URLs at different stages of the pipeline.

## Other decisions

I decided to use kotlinx serialization, in case this needed to
be a kotlin Multiplatform module or a part thereof, and I changed the
serialisaton name of the priceInCents field.

There are some libraries that could be updated. I was reluctant to
continue doing this since it involved changing KSP version along
with hilt and the kotlin plugin in a synchronised way that I did not
really have time to do.

Looking through the lint, there are some un-used resources that were
automatically created when I started with a blank project in Android
Studio. I left these as they are because it could have been time
consuming to remove them.






 