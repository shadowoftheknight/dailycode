# Copyright 2019 Google Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
variable "cicd_project_id" {
  type        = string
  description = "Project ID where the CICD service account should live"
}

variable "admin_project_id" {
  type        = string
  description = "Project where the admin service account lives, should probably be the Vault project itself."
}

variable "admin_service_account" {
  type        = string
  description = "Service account email that will be able to administrate Vault (i.e. root level permissions)"
}
