#include <linux/platform_device.h>
#include <linux/module.h>
#include "keyboard.h"

int init_ghid_module(void)
{
    int err = platform_device_register(&my_hid);
    if(err) {
        pr_err("Failed to register g_hid platform device\n");
	return err;
    }

    printk("Hello gHID World!\n");
    return 0;
}

void cleanup_ghid_module(void)
{
    platform_device_unregister(&my_hid);
    printk("Goodbye gHID World!\n");
}

module_init(init_ghid_module);
module_exit(cleanup_ghid_module);
MODULE_LICENSE("GPL");
