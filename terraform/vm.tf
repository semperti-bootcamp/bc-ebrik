provider "ovirt" {
  username = "ezequiel.brik@semperti.local"
  url = "http://rhev-m.semperti.local/ovirt-engine/api"
  password = "Bootcamp01!"
}
resource "ovirt_vm" "my_vm" {
name = "bootcamp-EBRIK"

network_interface {
label = "eth0"
boot_proto = "dhcp"

}

#attached_disks = [{
#disk_id = "${ovirt_disk.my_disk.id}"
#bootable = "true"
#interface = "virtio"
#}]
#
template = "centos7-cloud"
}

resource "ovirt_disk" "my_disk" {
name = "terraform_test"
size = 4096
format = "cow"
storage_domain_id = "43d4405f-4760-471c-b8e2-54e187d762e1"
sparse = true
}
