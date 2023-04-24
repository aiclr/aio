/* Packing buttons */
#include <gtk/gtk.h>

static void print_hello(GtkWidget *widget, gpointer data)
{
    /* g_print() with the string “Hello World” which will print Hello World in a terminal if the GTK application was started from one */
    g_print("Hello World\n");
}

static void activate(GtkApplication *app, gpointer user_data)
{
    GtkWidget *window;
    GtkWidget *grid;
    GtkWidget *button;

    /* create a new window, and set its title */
    window = gtk_application_window_new(app);
    gtk_window_set_title(GTK_WINDOW(window), "window");

    /* Here we construct the container that is going pack our buttons */
    grid = gtk_grid_new();
    /* Pack the container in the window */
    gtk_window_set_child(GTK_WINDOW(window), grid);

    button = gtk_button_new_with_label("Button 1");
    g_signal_connect(button, "clicked", G_CALLBACK(print_hello), NULL);

    /* Place the first button in the grid cell (0, 0), and make it fill
     * just 1 cell horizontally and vertically (ie no spanning)
     */
    gtk_grid_attach(GTK_GRID(grid), button, 0, 0, 1, 1);

    button = gtk_button_new_with_label("Button 2");
    g_signal_connect(button, "clicked", G_CALLBACK(print_hello), NULL);

    /* Place the second button in the grid cell (1, 0), and make it fill
     * just 1 cell horizontally and vertically (ie no spanning)
     */
    gtk_grid_attach(GTK_GRID(grid), button, 1, 0, 1, 1);

    button = gtk_button_new_with_label("Quit");
    g_signal_connect_swapped(button, "clicked", G_CALLBACK(gtk_window_destroy), window);

    /* Place the Quit button in the grid cell (0, 1), and make it
     * span 2 columns.
     */
    gtk_grid_attach(GTK_GRID(grid), button, 0, 1, 2, 1);

    /**
     * deprecated: 4.10
     * ‘gtk_widget_show’ is deprecated: Use 'gtk_widget_set_visible or gtk_window_present' instead
     *
     *  the window is then shown by GTK via gtk_widget_show()
     *
     *  void gtk_widget_show (GtkWidget* widget)
     *  gtk_widget_show(window);
     *
     *  void gtk_widget_set_visible (GtkWidget* widget,gboolean visible)
     *  gtk_widget_set_visible(window, TRUE);
     *  https://docs.gtk.org/gtk4/method.Widget.set_visible.html
     *
     *  void gtk_window_present(GtkWindow* window)
     *  gtk_window_present(GTK_WINDOW(window));
     *
     */
    gtk_window_present(GTK_WINDOW(window));
}

int main(int argc, char **argv)
{
    GtkApplication *app;
    int status;
    app = gtk_application_new("org.gtk.example", G_APPLICATION_DEFAULT_FLAGS);
    g_signal_connect(app, "activate", G_CALLBACK(activate), NULL);
    status = g_application_run(G_APPLICATION(app), argc, argv);
    g_object_unref(app);
    return status;
}