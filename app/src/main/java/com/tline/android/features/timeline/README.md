## Timeline _`(MVP)`_ ##

An Activity to be used for main application logic.

#### Main Functionality ####

- Holds action bar items `Language Switch` and `Logout`
- Holds a `Bottom Navigation bar` to provide links to all `three` types of lists
- Loads List of tweets from `Twitter API`
- Can switch to appropriate fragment according to bottom bar

#### Construction Elements / Dependencies ####

- Module to provide Dagger2 support
- Component
- Interactor
- Presenter
- XML layout

##### Presenter #####

- Provides `Interface` and implementation to manipulate UI and functionality for the Activity.
- Uses Decoupled classes for Functionality and Ui

##### Interactor #####

- Provides `interface` implementation of Use-cases, i.e. `OnFetchDataListener`


#### Propogates Changes ####

- By clicking a row, goes to `Details Dialog`
- By clicking on `EN` fron Action bar, shows data with English locale
- By clicking on `العربية` fron Action bar, shows data with Arabic locale
- By clicking on `Logout` fron Action bar, logs out the application
