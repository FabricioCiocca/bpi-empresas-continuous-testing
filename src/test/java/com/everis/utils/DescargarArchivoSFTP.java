package com.everis.utils;

import com.jcraft.jsch.*;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;

/**
 * A program demonstrates how to upload files from local computer to a remote
 * FTP server using Apache Commons Net API.
 *
 * @author www.codejava.net
 */
public class DescargarArchivoSFTP {

    public static void imCobrosMensualTxt(EnvironmentVariables environmentVariables) {

        String localFile = "/home/BIE_NEXBI/OUT/BIE/additional_charges/servicelevel/IMCobrosMensual.txt";
        String remoteFile = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("ruta.im");
        Session jschSession = null;

        String respDescargaImCobrosMensual = "";
        String username = "BIE_NEXBI";
        String password = "#Int3rb4nk#";
        String host = "ftpuat.grupoib.local";
        JSch jsch = new JSch();
        try {
            jschSession = jsch.getSession(username, host);
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        jschSession.setConfig(config);
        jschSession.setPassword(password);
        try {
            jschSession.connect();
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }

        ChannelSftp channelSftp = null;
        try {
            channelSftp = (ChannelSftp) jschSession.openChannel("sftp");
        } catch (JSchException e) {
            // throw the exception
        }
        try {
            channelSftp.connect();
        } catch (JSchException e) {
            // throw the exception
        }
        try {
            channelSftp.get(localFile, remoteFile);
            respDescargaImCobrosMensual = "Descarga Completa del Archivo IMCobrosMensual.txt";
        } catch (SftpException e) {
            // throw the exception
        }
        channelSftp.exit();
        Serenity.setSessionVariable("respDescargaImCobrosMensual").to(respDescargaImCobrosMensual);
    }

    public static void stCobrosMensualTxt(EnvironmentVariables environmentVariables) {

        String localFile = "/home/BIE_NEXBI/OUT/BIE/additional_charges/servicelevel/STCobrosMensual.txt";
        String remoteFile = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("ruta.st");
        Session jschSession = null;

        String respDescargaStCobrosMensual = "";
        String username = "BIE_NEXBI";
        String password = "#Int3rb4nk#";
        String host = "ftpuat.grupoib.local";
        JSch jsch = new JSch();
        try {
            jschSession = jsch.getSession(username, host);
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        jschSession.setConfig(config);
        jschSession.setPassword(password);
        try {
            jschSession.connect();
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }

        ChannelSftp channelSftp = null;
        try {
            channelSftp = (ChannelSftp) jschSession.openChannel("sftp");
        } catch (JSchException e) {
            // throw the exception
        }
        try {
            channelSftp.connect();
        } catch (JSchException e) {
            // throw the exception
        }
        try {
            channelSftp.get(localFile, remoteFile);
            respDescargaStCobrosMensual = "Descarga Completa del Archivo STCobrosMensual.txt";
        } catch (SftpException e) {
            // throw the exception
        }
        channelSftp.exit();
        Serenity.setSessionVariable("respDescargaStCobrosMensual").to(respDescargaStCobrosMensual);
    }

    private ChannelSftp setupJsch() throws JSchException {
        String username = "BIE_NEXBI";
        String password = "#Int3rb4nk#";
        String host = "ftpuat.grupoib.local:22220";
        JSch jsch = new JSch();
        Session jschSession = jsch.getSession(username, host);
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        jschSession.setConfig(config);
        jschSession.setPassword(password);
        jschSession.connect();
        return (ChannelSftp) jschSession.openChannel("sftp");
    }

    public boolean downloadSftp(String localFile, String remoteFile) {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = setupJsch();
        } catch (JSchException e) {
            // throw the exception
        }
        try {
            channelSftp.connect();
        } catch (JSchException e) {
            // throw the exception
        }
        try {
            channelSftp.get(localFile, remoteFile);
            System.out.println("Download Complete");
        } catch (SftpException e) {
            // throw the exception
        }
        channelSftp.exit();
        return true;
    }
}

