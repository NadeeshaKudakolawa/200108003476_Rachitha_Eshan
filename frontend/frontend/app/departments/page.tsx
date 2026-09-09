"use client";

import { useEffect, useState } from "react";
import { Department, getAllDepartments, createDepartment } from "@/services/departmentService";

export default function DepartmentsPage() {
  const [departments, setDepartments] = useState<Department[]>([]);
  const [name, setName] = useState("");
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState<string | null>(null);
  const [loading, setLoading] = useState(true);

  async function loadDepartments() {
    try {
      const data = await getAllDepartments();
      setDepartments(data);
    } catch (err) {
      setError("Could not load departments. Is the backend running?");
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadDepartments();
  }, []);

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    setError(null);
    setSuccess(null);

    if (!name.trim()) {
      setError("Department name is required.");
      return;
    }

    try {
      await createDepartment({ name });
      setSuccess(`Department "${name}" created successfully.`);
      setName("");
      loadDepartments();
    } catch (err: any) {
      setError(err.message || "Something went wrong.");
    }
  }

  return (
    <main style={{ maxWidth: 500, margin: "40px auto", fontFamily: "sans-serif" }}>
      <h1>Departments</h1>

      <form onSubmit={handleSubmit} style={{ marginBottom: 24 }}>
        <input
          type="text"
          placeholder="Department name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          style={{ padding: 8, width: "70%", marginRight: 8 }}
        />
        <button type="submit" style={{ padding: 8 }}>
          Add Department
        </button>
      </form>

      {error && <p style={{ color: "red" }}>{error}</p>}
      {success && <p style={{ color: "green" }}>{success}</p>}

      {loading ? (
        <p>Loading...</p>
      ) : (
        <ul>
          {departments.map((d) => (
            <li key={d.id}>
              #{d.id} — {d.name}
            </li>
          ))}
        </ul>
      )}
    </main>
  );
}