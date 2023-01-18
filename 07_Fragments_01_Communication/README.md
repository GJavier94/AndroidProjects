# Android Projects
### The purpose of this section is to learn and put in practice the next topics.

__07_Fragments_01_Communication__:<br>
> - How to stablish communications between Fragments by using built in listeners and ViewModel
  
- Concepts, Classes,...
  - __Parcelable__
     - writeToParcel
  - __Parcelable.Creator<T>__
    - createFromParcel
    - newArray
  - androidx.fragment.app
    -  __by viewModels()__
    - __by activityViewModels()__
    - setFragmentResult
    - setFragmentResultListener
  - __bundle__ 
    - putParcelable
- Kotlin/Java topics
  - __@JvmField__

### Fragment communication API

__FragmentManager__ Class implement __FragmentResultOwner__
__FragmentResultOwner__: has many methods  two important ones are
  - __setFragmentResult__( bundlekey:String, bundle:Bundle) -> sends a result so the sender used it
  - __setFragmentResultListener__(bundlekey:String, bundle:Bundle) -> receives the result

A fragment has two __FragmentManager__
  - __parentFragmentManager__ : which is the __FM__ from the parent so you can comunicate with your siblings.
  - __childFragmentManager__  : so you save all the fragments Children you own.

__Fragments__ have methods and have the property __FragmentManager__ so it hides the call in the __FM__, on the other hand activities retrieve __supportFragmentManager__ and then call __setFragmentResult()__.


