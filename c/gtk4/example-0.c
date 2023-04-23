/**
 * All GTK applications will, of course, include gtk/gtk.h,
 * which declares functions, types and macros(宏) required by GTK applications
 */
#include <gtk/gtk.h>

/**
 * This is where we construct our GTK window, so that a window is shown when the application is launched.
 *
 * The window will have a frame, a title bar, and window controls depending on the platform.
 */
static void activate(GtkApplication *app, gpointer user_date)
{
	GtkWidget *window;
	/* The call to gtk_application_window_new() will create a new GtkApplicationWindow and store it inside the window pointer. */
	window = gtk_application_window_new(app);
	/**
	 * A window title is set using gtk_window_set_title().
	 * 
	 * This function takes a GtkWindow pointer and a string as input. 
	 * As our window pointer is a GtkWidget pointer, 
	 * we need to cast it to GtkWindow;
	 * instead of casting window via a typical C cast like (GtkWindow*), 
	 * window can be cast using the macro(宏) GTK_WINDOW().
	 * 
	 * GTK_WINDOW() will check if the pointer is an instance of the GtkWindow class, before casting, 
	 * and emit(发出)) a warning if the check fails. 
	 * More information about this convention can be found in the GObject documentation https://docs.gtk.org/gobject/concepts.html#conventions.
	 */
	gtk_window_set_title(GTK_WINDOW(window), "Window");
	/* the window size is set using gtk_window_set_default_size() */
	gtk_window_set_default_size(GTK_WINDOW(window), 200, 200);
	/* the window is then shown by GTK via gtk_widget_show() */
	gtk_widget_show(window);
}

int main(int argc, char **argv)
{
	/* declare a GtkApplication pointer */
	GtkApplication *app;
	int status;
	/**
	 * initialized using gtk_application_new()
	 *
	 * pick an application identifier (a name "org.gtk.example" )
	 * and pass it to gtk_application_new() as parameter.
	 *
	 * For GLib versions older than 2.74
	 * you will need to replace G_APPLICATION_DEFAULT_FLAGS with G_APPLICATION_FLAGS_NONE
	 *
	 * GApplicationFlags
	 *
	 */
	app = gtk_application_new("org.gtk.example", G_APPLICATION_FLAGS_NONE);
	/**
	 * the activate signal is connected to the activate() function
	 *
	 */
	g_signal_connect(app, "activate", G_CALLBACK(activate), NULL);
	/**
	 * The activate signal will be emitted(发出) when your application is launched with g_application_run()
	 * The g_application_run() call also takes as arguments the command line arguments (the argc count and the argv string array)
	 * Your application can override the command line handling, e.g. to open files passed on the commandline
	 *
	 * Within g_application_run() the activate signal is sent and we then proceed into the activate() function of the application.
	 */
	status = g_application_run(G_APPLICATION(app), argc, argv);
	/**
	 * When you close the window, by (for example) pressing the X button,
	 * the g_application_run() call returns with a number which is saved inside an integer variable named status. 
	 * Afterwards, the GtkApplication object is freed from memory with g_object_unref(). 
	 * Finally the status integer is returned and the application exits
	*/
	g_object_unref(app);
	return status;
}
