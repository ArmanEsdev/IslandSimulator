# 🏝️ Island Ecosystem Simulator

Simulador interactivo por consola que representa un ecosistema en una isla donde animales, plantas y depredadores conviven, se reproducen y sobreviven en ciclos multihilo configurables.

---

## 📦 Contenido

- Interfaz de menú interactiva (`MenuShow`)
- Configuración dinámica de simulación
- Ecosistema con animales, plantas y comportamiento autónomo
- Registro histórico de población
- Visualización pseudográfica por consola
- Inserción manual de animales en tiempo real
- Guardado y carga automática de configuración desde archivo `.properties`

---

## 🚀 Ejecución

1. **Compilar:** Asegurate de compilar el proyecto (por ejemplo, desde IntelliJ o CLI con `javac`).
2. **Ejecutar:** Correr `Main.java`.
3. **Usar el menú:** Explorar opciones 1 a 7 para simular, ver mapas y editar configuración.

> **📌 Nota:** Al cambiar la configuración o cerrar el programa, los cambios se guardan en un archivo `island-config.properties` generado automáticamente.

---

## ⚙️ Configuración persistente

La configuración se guarda en:

```properties
island-config.properties```
