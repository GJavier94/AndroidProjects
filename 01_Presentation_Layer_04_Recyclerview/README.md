
# Android Projects
### The purpose of this section is to learn and implement in practice the next topics.

__01_Presentation_Layer_04_Recyclerview__:<br>
> - Understanding Recycler View as a improvement of List View
  
- Concepts, Classes,...
  - RecyclerView
    - Behavior: 
      - attrs: layoutManager
      - tools attrs: listitem
    - Adapter (oop Design pattern)
      - RecyclerView.Adapter<Class extends ViewHolderAlarm>
        - onCreateViewHolder
        - onBindViewHolder
        - getItemCount
        - notifyDataSetChanged
      - behaviour: createAdapter
    - RecyclerView.ViewHolder

### Definitions and overview
- __RecyclerView__: It's a listview which it has dynamic content e.g. It can reuse its viewholders to show data from a data source, the data source can have many items more than viewholder number.
- __ViewHolder__: It's the holder of the view.
- __View__: It's the way in which the data source is shown/
- __DataSource__: Data source that can come from a content provider, database, rest API, array of strings, xml resources.

In order to change the data that the viewholder is showing is necessary to have and adapter so that the adapter binds data elements from the data source to the viewholder according to the interactions with the user
the RecyclerView class has two inner clases:

1. __ViewHolder__ which is the class how shows the view and changes the data  and also defines listenes so it receives a view
1. __Adapter<ViewHolder>__ (refDataSource) it's a generic Adapter which receives the datasource reference ( list of adaptees) and
and has thre main methods:
    1. __onCreateViewHolder__: It is the one which given a view it inflates the view and creates a view holder which has the view so the layout manager calls this methods when creating viewholder that are going to be reused, it can be for example 5.
    1. __onBindViewHolder__: After having created a viewholder this is called as the method to bind the adaptee(datasource and the target) the recycler view
    1. it is called when putting the first viewholders and also when recycling them for other datasource each data source has a position

