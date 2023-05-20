FROM fedora:22
RUN useradd me
RUN dnf install -y mutter gnome-settings-daemon tigervnc-server-minimal eclipse-pde
COPY ./init.sh /
RUN chmod a+x init.sh
CMD [ "/init.sh" ]