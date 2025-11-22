import express from "express";
import cors from "cors";
import bodyParser from "body-parser";
import { GoogleGenerativeAI } from "@google/generative-ai";
import dotenv from "dotenv";
dotenv.config();

const app = express();
app.use(cors());
app.use(bodyParser.json());

const genAI = new GoogleGenerativeAI(process.env.GEMINI_API_KEY);

const sessions = {};

const products = [
  { name: "Mesa", price: 100 },
  { name: "Silla", price: 70 },
  { name: "Sillón", price: 90 },
  { name: "Perchero", price: 80 }
];

app.post("/chat", async (req, res) => {
  try {
    const { sessionId, chatInput } = req.body;

    if (!sessionId) {
      return res.status(400).json({ error: "sessionId es obligatorio" });
    }

    if (!chatInput) {
      return res.status(400).json({ error: "chatInput es obligatorio" });
    }

    if (!sessions[sessionId]) {
      sessions[sessionId] = [];
    }

    sessions[sessionId].push({ role: "user", text: chatInput });

    const systemPrompt = `
Eres un chatbot para responder cualquier pregunta, charlar.
Si te preguntan por productos y precios, responde usando SOLO la siguiente lista de productos y precios.

Aquí está la lista de productos disponibles:
${JSON.stringify(products, null, 2)}

Reglas:
- SOLO puedes responder sobre estos productos.
- Si preguntan por algo que no está en la lista, responde que no está disponible.
    `;

    const geminiHistory = [
      {
        role: "user",
        parts: [{ text: systemPrompt }]
      },
      ...sessions[sessionId].map(msg => ({
        role: msg.role === "model" ? "model" : "user",
        parts: [{ text: msg.text }]
      }))
    ];

    const model = genAI.getGenerativeModel({ model: "gemini-2.5-flash" });

    const result = await model.generateContent({
      contents: geminiHistory,
      generationConfig: {
        maxOutputTokens: 300
      }
    });

    const output = result.response.text();

    sessions[sessionId].push({ role: "model", text: output });

    res.json({
      sessionId,
      output,
      history: sessions[sessionId]
    });

  } catch (error) {
    console.error("Error:", error);
    res.status(500).json({
      error: true,
      message: error.message
    });
  }
});

app.listen(3000, () => {
  console.log("Servidor corriendo en http://localhost:3000");
});
