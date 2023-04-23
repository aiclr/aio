#include <gtk/gtk.h>

static void print_hello(GtkWidget *widget,gpointer data)
{
    /* g_print() with the string “Hello World” which will print Hello World in a terminal if the GTK application was started from one */
    g_print("Hello World\n");
}

static void activate(GtkApplication *app,gpointer user_data)
{
    GtkWidget *window;
    GtkWidget *button;
    GtkWidget *box;

    window = gtk_application_window_new(app);
    gtk_window_set_title(GTK_WINDOW(window),"window");
    gtk_window_set_default_size(GTK_WINDOW(window),200,200);

    /**
     * The GtkBox widget is created with gtk_box_new(),
     * which takes a GtkOrientation enumeration value as parameter.
     * The buttons which this box will contain can either be laid out horizontally or vertically.
     * This does not matter in this particular case, as we are dealing with only one button.
     * After initializing box with the newly created GtkBox
    */
    box=gtk_box_new(GTK_ORIENTATION_VERTICAL,0);
    gtk_widget_set_halign(box,GTK_ALIGN_CENTER);
    gtk_widget_set_valign(box,GTK_ALIGN_CENTER);
    /* adds the box widget to the window widget using gtk_window_set_child(). */
    gtk_window_set_child(GTK_WINDOW(window),box);

    /**
     * the button variable is initialized in similar manner. gtk_button_new_with_label() is called which returns a GtkButton to be stored in button.
     * Afterwards .
    */
    button=gtk_button_new_with_label("Hello World");
    /**
     * Using g_signal_connect(), the button is connected to a function in our app called print_hello() 
     * so that when the button is clicked, GTK will call this function
     * As the print_hello() function does not use any data as input, NULL is passed to it
     */
    g_signal_connect(button,"clicked",G_CALLBACK(print_hello),NULL);
    /**
     * After connecting print_hello(), another signal is connected to the “clicked” state of the button using g_signal_connect_swapped(). 
     * This functions is similar to a g_signal_connect(), with the difference lying in how the callback function is treated; 
     * g_signal_connect_swapped() allows you to specify what the callback function should take as parameter by letting you pass it as data.
     * In this case the function being called back is gtk_window_destroy() and the window pointer is passed to it. 
     * This has the effect that when the button is clicked, the whole GTK window is destroyed.
     * 
     * In contrast if a normal g_signal_connect() were used to connect the “clicked” signal with gtk_window_destroy(), 
     * then the function would be called on button (which would not go well, since the function expects a GtkWindow as argument).
    */
    g_signal_connect_swapped(button,"clicked",G_CALLBACK(gtk_window_destroy),window);

    /* button is added to our box */
    gtk_box_append(GTK_BOX(box),button);


    /* the window is then shown by GTK via gtk_widget_show() */
    gtk_widget_show(window);
}

int main(int argc,char **argv)
{
    GtkApplication *app;
    int status;
    app=gtk_application_new("org.gtk.example",G_APPLICATION_DEFAULT_FLAGS);
    g_signal_connect(app,"activate",G_CALLBACK(activate),NULL);
    status=g_application_run(G_APPLICATION(app),argc,argv);
    g_object_unref(app);
    return status;
}