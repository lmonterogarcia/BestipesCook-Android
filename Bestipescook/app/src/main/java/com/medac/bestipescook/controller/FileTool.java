package com.medac.bestipescook.controller;

public class FileTool {
    /*
    public static boolean uploadFile(String url, int port, String username,
                                     String password, String path, String filename, InputStream input) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        // LogUitl.Infor(url+port+username+password+path);


        try {
            int reply;
            ftp.connect(url, port);// Conectarse al servidor FTP
            // Si usa el puerto predeterminado, puede usar ftp.connect (url) para conectarse directamente al servidor FTP
            ftp.login(username, password);//acceso
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.setFileType(FTP.BINARY_FILE_TYPE);// El formato de datos de la imagen cargada () debe escribir esto, de lo contrario no se abrirá en el servidor
            if (!ftp.changeWorkingDirectory(path)) {
                if (ftp.makeDirectory(path)) {
                    ftp.changeWorkingDirectory(path);
                }
            }
            //  ftp.changeWorkingDirectory(path);
            // Agrega esta oración al configurarla en otros puertos
            //  ftp.enterLocalPassiveMode();
            ftp.storeFile(filename, input);
            input.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    public void formatear(){
        Bundle extras = data.getExtras();
        if (extras != null) {
            String name = creatTime + ".jpg"; // La imagen lleva el nombre de la hora del sistema
            Bundle bundle = data.getExtras();
            // Obtenga los datos devueltos por la cámara y conviértalos a formato de imagen de mapa de bits
            Bitmap bitmap = (Bitmap) bundle.get("data");
            FileOutputStream b = null;
            File file = new File(getAlbumStorageDir("upload"), name); // upload es el nombre de la carpeta que se volverá a guardar después de recortar la imagen
            try {
                b = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// escribir datos en un archivo
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (b != null) {
                        b.flush();
                        b.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void subirImage(){
        try {
            FileInputStream in = new FileInputStream(photoFile);
            // Reemplaza la siguiente información con la que necesitas
            boolean flag = FileTool.uploadFile("Nombre de host del servidor FTP", 21,"nombre de inicio de sesión", "contraseña de inicio de sesión", "El nombre de la carpeta a pasar (si el servidor no tiene esta carpeta, la carpeta se puede crear automáticamente, sin creación manual)", photoFile.getName(), in);
            if (flag == true) {
                handler.sendEmptyMessage(1);
            } else {
                handler.sendEmptyMessage(2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
     */
}
