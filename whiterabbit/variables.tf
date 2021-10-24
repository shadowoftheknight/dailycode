variable "region" {
  description = "GCP regions e.g us-west1"
  type        = string
  default = "us-west1"
}

variable "project_id" {
  description = "project id"
  type        = string
  default = "white-rabbit-328319"
}

variable "kms_keyring" {
  description = "kms_keyring"
  type        = string
  default = "zion"
}

variable "kms_crypto_key" {
  description = "kms_crypto_key"
  type        = string
  default = "matrix-one"
}