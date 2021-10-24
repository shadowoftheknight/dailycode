// Configure the Google Cloud provider
provider "google" {
 credentials = file("white-rabbit-328319-4f03ba566eee.json")
 project     = var.project_id
 region      = var.region
}

module "vault" {
  source         = "terraform-google-modules/vault/google"
  project_id     = var.project_id
  region         = var.region
  kms_keyring    = var.kms_keyring
  kms_crypto_key = var.kms_crypto_key
}

// Terraform plugin for creating random ids
resource "random_id" "instance_id" {
 byte_length = 8
}

// A single Compute Engine instance
resource "google_compute_instance" "default" {
 name         = "flask-vm-${random_id.instance_id.hex}"
 machine_type = "f1-micro"
 zone         = "us-west1-a"

 boot_disk {
   initialize_params {
     image = "debian-cloud/debian-9"
   }
 }

// Make sure flask is installed on all new instances for later steps
 metadata_startup_script = "sudo apt-get update; sudo apt-get install -yq build-essential python-pip rsync; pip install flask"

 network_interface {
   network = "default"

   access_config {
     // Include this section to give the VM an external ip address
   }
 }
}

// A variable for extracting the external IP address of the instance
output "ip" {
 value = google_compute_instance.default.network_interface.0.access_config.0.nat_ip
}
