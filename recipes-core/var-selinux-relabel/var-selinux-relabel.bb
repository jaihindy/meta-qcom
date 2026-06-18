SUMMARY = "Relabel /var after mount for SELinux"
DESCRIPTION = "Ensures correct labeling of /var when mounted after early boot (OSTree systems)"
#LICENSE = "QCOM"
#LIC_FILES_CHKSUM = "file://${LAYERDIR}/licenses/LICENSE.qcom;md5=164e3362a538eb11d3ac51e8e134294b"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
REQUIRED_DISTRO_FEATURES = "selinux"

SRC_URI = "file://var-selinux-relabel.service"

inherit systemd features_check

SYSTEMD_SERVICE:${PN} = "var-selinux-relabel.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/sources/var-selinux-relabel.service \
        ${D}${systemd_system_unitdir}/
}

FILES:${PN} += "${systemd_system_unitdir}/var-selinux-relabel.service"
