## Login ##

An Activity to provide functionality for login


#### Main Functionality ####

- Show Twitter Login button
- Launch twitter auth page
- Launch next activity on success
- Show Error in case of failure

#### Construction Elements / Dependencies ####

- Uses [Login with Twitter](https://dev.twitter.com/twitterkit/android/log-in-with-twitter)
- Module to provide Dagger2 support 
- Component
- Interactor
- Presenter
- XML layout

##### Presenter #####

- Provides `Interface` and implementation to manipulate UI and functionality for the Activity.
- Uses Decoupled classes for Functionality and Ui
- Implemets `LoginPresenter`

#### Propogates Changes ####

- `Twitter Login Button`, initializes the Signin process
- `Logout`, initializes the Logout process

