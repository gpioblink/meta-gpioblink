#include <linux/module.h>
#include <linux/usb/composite.h>
#include <linux/usb/functionfs.h>
#include <linux/usb/g_hid.h>

/* HIDレポートデスクリプタ */
static const struct hidg_func_descriptor my_hid_data = {
    .subclass        = 0, /* No subclass */
    .protocol        = 1, /* Keyboard */
    .report_length   = 8,
    .report_desc_length = 63,
    .report_desc     = {
        0x05, 0x01,     /* USAGE_PAGE (Generic Desktop)           */
        0x09, 0x06,     /* USAGE (Keyboard)                       */
        0xa1, 0x01,     /* COLLECTION (Application)               */
        0x05, 0x07,     /*   USAGE_PAGE (Keyboard)                */
        0x19, 0xe0,     /*   USAGE_MINIMUM (Keyboard LeftControl) */
        0x29, 0xe7,     /*   USAGE_MAXIMUM (Keyboard Right GUI)   */
        0x15, 0x00,     /*   LOGICAL_MINIMUM (0)                  */
        0x25, 0x01,     /*   LOGICAL_MAXIMUM (1)                  */
        0x75, 0x01,     /*   REPORT_SIZE (1)                      */
        0x95, 0x08,     /*   REPORT_COUNT (8)                     */
        0x81, 0x02,     /*   INPUT (Data,Var,Abs)                 */
        0x95, 0x01,     /*   REPORT_COUNT (1)                     */
        0x75, 0x08,     /*   REPORT_SIZE (8)                      */
        0x81, 0x03,     /*   INPUT (Cnst,Var,Abs)                 */
        0x95, 0x05,     /*   REPORT_COUNT (5)                     */
        0x75, 0x01,     /*   REPORT_SIZE (1)                      */
        0x05, 0x08,     /*   USAGE_PAGE (LEDs)                    */
        0x19, 0x01,     /*   USAGE_MINIMUM (Num Lock)             */
        0x29, 0x05,     /*   USAGE_MAXIMUM (Kana)                 */
        0x91, 0x02,     /*   OUTPUT (Data,Var,Abs)                */
        0x95, 0x01,     /*   REPORT_COUNT (1)                     */
        0x75, 0x03,     /*   REPORT_SIZE (3)                      */
        0x91, 0x03,     /*   OUTPUT (Cnst,Var,Abs)                */
        0x95, 0x06,     /*   REPORT_COUNT (6)                     */
        0x75, 0x08,     /*   REPORT_SIZE (8)                      */
        0x15, 0x00,     /*   LOGICAL_MINIMUM (0)                  */
        0x25, 0x65,     /*   LOGICAL_MAXIMUM (101)                */
        0x05, 0x07,     /*   USAGE_PAGE (Keyboard)                */
        0x19, 0x00,     /*   USAGE_MINIMUM (Reserved)             */
        0x29, 0x65,     /*   USAGE_MAXIMUM (Keyboard Application) */
        0x81, 0x00,     /*   INPUT (Data,Ary,Abs)                 */
        0xc0            /* END_COLLECTION                         */
    }
};

static struct usb_configuration configuration = {
        .label                  = "Keyboard",
        .bConfigurationValue    = 1,
        .bmAttributes           = USB_CONFIG_ATT_SELFPOWER,
};

static struct usb_function_instance *hidg_func_inst;
static struct usb_function *hidg_func;

static int my_hid_bind(struct usb_composite_dev *cdev)
{
    struct usb_gadget *gadget = cdev->gadget;
    int ret;

    pr_info("my_hid_bind called\n");

    ret = usb_string_ids_tab(cdev, strings_dev);
    if (ret < 0)
        return ret;

    device_desc.iManufacturer = strings_dev[USB_GADGET_MANUFACTURER_IDX].id;
    device_desc.iProduct = strings_dev[USB_GADGET_PRODUCT_IDX].id;
    device_desc.iSerialNumber = strings_dev[USB_GADGET_SERIAL_IDX].id;

    hidg_func_inst = usb_get_function_instance("hid");
    if (IS_ERR(hidg_func_inst)) {
        pr_err("Failed to get HID function instance\n");
        return PTR_ERR(hidg_func_inst);
    }

    hidg_func = usb_get_function(hidg_func_inst);
    if (IS_ERR(hidg_func)) {
        pr_err("Failed to get HID function\n");
        ret = PTR_ERR(hidg_func);
        usb_put_function_instance(hidg_func_inst);
        return ret;
    }

    ret = usb_add_function(&configuration, hidg_func);
    if (ret) {
        pr_err("Failed to add HID function\n");
        usb_put_function(hidg_func);
        usb_put_function_instance(hidg_func_inst);
        return ret;
    }

    pr_info("%s: registered %s at %s speed\n",
            gadget->name, cdev->driver->name,
            usb_speed_string(gadget->speed));

    return 0;
}

static int my_hid_unbind(struct usb_composite_dev *cdev)
{
    pr_info("my_hid_unbind called\n");
    usb_put_function(hidg_func);
    usb_put_function_instance(hidg_func_inst);
    return 0;
}

static struct usb_device_descriptor device_desc = {
        .bLength            = sizeof device_desc,
        .bDescriptorType    = USB_DT_DEVICE,
        .bDeviceClass       = USB_CLASS_MISC,
        .bDeviceSubClass    = 0x02,
        .bDeviceProtocol    = 0x01,
        .bcdUSB             = cpu_to_le16(0x0200),
        .idVendor           = cpu_to_le16(_ID_VENDOR),
        .idProduct          = cpu_to_le16(_ID_PRODUCT),
        .bcdDevice          = cpu_to_le16(_BCD_DEVICE),
        .bNumConfigurations = 1,
};

static struct usb_gadget_strings stringtab_dev = {
        .language   = 0x0409,
        .strings    = strings_dev,
};

static struct usb_string strings_dev[] = {
        [USB_GADGET_MANUFACTURER_IDX].s = "gpioblink-manufacturer",
        [USB_GADGET_PRODUCT_IDX].s      = "gpioblink-product",
        [USB_GADGET_SERIAL_IDX].s       = "gpioblink-serial",
        {}
};

static struct usb_gadget_strings *usb_strings[] = {
        &stringtab_dev,
        NULL,
};

static struct usb_composite_driver my_hid_driver = {
    .name = "my_hid",
    .dev = &device_desc,
    .strings = usb_strings,
    .max_speed = USB_SPEED_FULL,
    .bind = my_hid_bind,
    .unbind = my_hid_unbind,
};

module_usb_composite_driver(my_hid_driver);

MODULE_LICENSE("GPL");

