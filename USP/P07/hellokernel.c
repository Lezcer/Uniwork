#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/init.h>

static int __init hello_init()
    {
        printk("Hello World!\n")
    }

static void __exit hello_exit()
    {
        printk("exit\n");
    }
module_init(hello_init);
module_exit(hello_exit);
