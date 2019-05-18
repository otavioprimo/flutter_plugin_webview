package br.com.codebrain.nativeweb;

import io.flutter.plugin.common.PluginRegistry.Registrar;

/** NativewebPlugin */
public class NativewebPlugin {
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    registrar
            .platformViewRegistry()
            .registerViewFactory("nativeweb", new WebFactory(registrar));
  }
}
