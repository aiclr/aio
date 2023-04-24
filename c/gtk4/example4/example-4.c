#include <gtk/gtk.h>
#include <glib/gstdio.h>

static void print_hello(GtkWidget *wodget, gpointer data)
{
    g_print("Hello World\n");
}

static void quit_cb(GtkWindow *window)
{
    gtk_window_close(window);
}
/**
 * GtkBuilder can also be used to construct objects that are not widgets, such as tree models, adjustments, etc.
 * That is the reason the method we use here is called gtk_builder_get_object() and returns a GObject instead of a GtkWidget
 * 
 * Using GResource it is possible to combine the best of both worlds: 
 *      you can keep the UI definition files separate inside your source code repository,
 *      and then ship them embedded(把...嵌入) into your application.
 */
static void activate(GtkApplication *app, gpointer user_data)
{
    GtkBuilder *builder = gtk_builder_new();
    /**
     * Normally, you would pass a full path to gtk_builder_add_from_file() to make the execution of your program independent of the current directory.
     * A common location to install UI descriptions and similar data is /usr/share/appname
     *
     * It is also possible to embed(把...嵌入) the UI description in the source code as a string and use gtk_builder_add_from_string() to load it.
     * But keeping the UI description in a separate file has several advantages:
     *   it is possible to make minor adjustments to the UI without recompiling your program
     *   it is easier to isolate the UI code from the business logic of your application
     *   it is easier to restructure your UI into separate classes using composite widget templates
     */
    gtk_builder_add_from_file(builder, "builder.ui", NULL);

    GObject *window = gtk_builder_get_object(builder, "window");
    gtk_window_set_application(GTK_WINDOW(window), app);

    GObject *button = gtk_builder_get_object(builder, "button1");
    g_signal_connect(button, "clicked", G_CALLBACK(print_hello), NULL);

    button = gtk_builder_get_object(builder, "button2");
    g_signal_connect(button, "clicked", G_CALLBACK(print_hello), NULL);

    button = gtk_builder_get_object(builder, "quit");
    g_signal_connect_swapped(button, "clicked", G_CALLBACK(quit_cb), window);

    gtk_widget_show(GTK_WINDOW(window));

    g_object_unref(builder);
}

int main(int argc, char **argv)
{
#ifdef GTK_SRCDIR
    g_chdir(GTK_SRCDIR);
#endif
    GtkApplication *app = gtk_application_new("org.gtk.example", G_APPLICATION_DEFAULT_FLAGS);
    g_signal_connect(app, "activate", G_CALLBACK(activate), NULL);
    int status = g_application_run(G_APPLICATION(app), argc, argv);
    g_object_unref(app);
    return status;
}