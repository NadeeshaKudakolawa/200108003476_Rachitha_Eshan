"use client";

import { FormEvent, useEffect, useState } from "react";
import {
  createTrainingProgram,
  getAllTrainingPrograms,
  TrainingProgram,
} from "@/services/trainingProgramService";

export default function TrainingProgramsPage() {
  const [programs, setPrograms] = useState<TrainingProgram[]>([]);
  const [name, setName] = useState("");
  const [capacity, setCapacity] = useState("10");
  const [message, setMessage] = useState("");

  async function load() {
    try {
      setPrograms(await getAllTrainingPrograms());
    } catch (error) {
      setMessage(error instanceof Error ? error.message : "Could not load programmes.");
    }
  }

  useEffect(() => {
    load();
  }, []);

  async function submit(event: FormEvent) {
    event.preventDefault();
    try {
      await createTrainingProgram({
        name,
        maximumParticipants: Number(capacity),
      });
      setName("");
      setMessage("Training programme created.");
      await load();
    } catch (error) {
      setMessage(error instanceof Error ? error.message : "Could not create programme.");
    }
  }

  return (
    <main style={{ maxWidth: 700, margin: "40px auto", fontFamily: "sans-serif" }}>
      <h1>Training programmes</h1>
      <form onSubmit={submit} style={{ display: "flex", gap: 8, marginBottom: 20 }}>
        <input required placeholder="Programme name" value={name} onChange={(e) => setName(e.target.value)} />
        <input required min="1" type="number" value={capacity} onChange={(e) => setCapacity(e.target.value)} />
        <button type="submit">Add programme</button>
      </form>
      <p>{message}</p>
      <ul>
        {programs.map((program) => (
          <li key={program.id}>
            #{program.id} — {program.name} (capacity: {program.maximumParticipants})
          </li>
        ))}
      </ul>
    </main>
  );
}
